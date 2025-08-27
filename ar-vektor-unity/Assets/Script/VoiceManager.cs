using UnityEngine;

public class VoiceManager : MonoBehaviour
{
    [Header("Daftar AudioSource (isi di Inspector)")]
    public AudioSource[] audioSources;
    [Header("AudioClip yang dimainkan per objek (opsional, kalau ingin custom)")]
    public AudioClip[] clips;

    public void PlayAllVoices()
    {
        for (int i = 0; i < audioSources.Length; i++)
        {
            if (audioSources[i] != null)
            {
                // Jika ingin mengganti clip sebelum play
                if (clips != null && i < clips.Length && clips[i] != null)
                {
                    audioSources[i].clip = clips[i];
                }
                audioSources[i].Stop(); // Optional, agar suara tidak overlap
                audioSources[i].Play();
            }
        }
    }

    public void StopAllVoices()
    {
        foreach (var source in audioSources)
        {
            if (source != null) source.Stop();
        }
    }
}
