using UnityEngine;

public class AnimationManager : MonoBehaviour
{
    [Header("Daftar Animator (isi di Inspector)")]
    public Animator[] animators;
    [Header("Nama state animasi per objek (isi di Inspector, urut sesuai animator)")]
    public string[] animationStates;

    public void ResetAllAnimations()
    {
        for (int i = 0; i < animators.Length; i++)
        {
            if (animators[i] != null && !string.IsNullOrEmpty(animationStates[i]))
            {
                animators[i].Play(animationStates[i], -1, 0f);
            }
        }
    }
}
